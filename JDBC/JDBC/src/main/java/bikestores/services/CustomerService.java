package bikestores.services;

import bikestores.dao.CustomerDao;
import bikestores.dto.CustomerDto;
import bikestores.exceptions.ServiceException;
import bikestores.models.Customer;
import bikestores.utils.ScannerUtils;
import bikestores.utils.ValidatorUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerService implements IService <Customer> {
    private final Scanner scanner;
    private final CustomerDao customerDao;

    public CustomerService(Scanner scanner, Connection conn) {
        this.scanner = scanner;
        customerDao = new CustomerDao(conn);
    }

    public ArrayList<Customer> selectAll(int id, String name, String phone, String email) {
        try {
            return customerDao.selectAll(id, name, phone, email);
        } catch (SQLException e) {
            throw new ServiceException("Cannot get all customers: " + e.getMessage());
        }
    }

    @Override
    public Customer getByID(int id) {
        try {
            Customer customer = customerDao.getByID(id);
            if(customer == null) {
                throw new ServiceException("ID not found!");
            } else return customer;
        } catch (SQLException e) {
            throw new ServiceException("Cannot find this customer: " + e.getMessage());
        }
    }

    @Override
    public int insert() {
        try {
            CustomerDto customerDto = createCustomerDtoForInsert(-1);
            return customerDao.insert(customerDto);

        } catch (SQLException e) {
            throw new ServiceException("Cannot insert this customer! " + e.getMessage());
        }
    }

    @Override
    public Customer update() {
        try {
            int id = ScannerUtils.readPositiveInt(scanner, "Enter customer ID: ", false);
            Customer oldCustomer = getByID(id);
            CustomerDto newCustomer = createCustomerDtoForUpdate(id);

            if(newCustomer.getName() == null) {
                newCustomer.setEmail(oldCustomer.getName());
            } else {
                oldCustomer.setName(newCustomer.getName());
            }

            if(newCustomer.getGender() == null) {
                newCustomer.setGender(oldCustomer.getGender());
            } else {
                oldCustomer.setGender(newCustomer.getGender());
            }

            if(newCustomer.getPhone() == null) {
                newCustomer.setPhone(oldCustomer.getPhone());
            } else {
                oldCustomer.setPhone(newCustomer.getPhone());
            }

            if(newCustomer.getEmail() == null) {
                newCustomer.setEmail(oldCustomer.getEmail());
            } else {
                oldCustomer.setEmail(newCustomer.getEmail());
            }

            return customerDao.update(id, newCustomer) ? oldCustomer : null;
        } catch (SQLException e) {
            throw new ServiceException("Cannot update this customer! " + e.getMessage());
        }
    }

    @Override
    public boolean delete() {
        try {
            int id = ScannerUtils.readPositiveInt(scanner, "Enter customer ID: ", false);
            Customer customer = getByID(id);

            if(customerDao.isReferencedByOtherTables(id)) {
                throw new ServiceException("Cannot delete customer with existing order, wishlist or review!");
            }

            return customerDao.delete(id);
        } catch (SQLException e) {
            throw new ServiceException("Cannot delete this customer! " + e.getMessage());
        }
    }

    private CustomerDto createCustomerDtoForInsert(int id) throws SQLException {
        String name = ScannerUtils.readNonEmpty(scanner, "Enter customer's name: ");
        String gender = ScannerUtils.readValidGender(scanner, "Enter customer's gender: ", false);
        String phone = readValidPhone(id, "Enter customer's phone: ", false);
        String email = readValidEmail(id, "Enter customer's email: ", false);

        return new CustomerDto(name, gender, phone,  email);
    }

    private CustomerDto createCustomerDtoForUpdate(int id) throws SQLException {
        String name = ScannerUtils.readNullableString(scanner, "Enter customer's name (blank to skip): ");
        String gender = ScannerUtils.readValidGender(scanner, "Enter customer's gender (female/ male/ other) (blank to skip): ", true);
        String phone = readValidPhone(id, "Enter customer's phone (blank to skip): ", true);
        String email = readValidEmail(id, "Enter customer's email (blank to skip): ", true);

        return new CustomerDto(name, gender, phone, email);
    }

    private String readValidEmail (int id, String prompt, boolean isNullable) {
        try {
            String line;

            while (true) {
                line = isNullable
                        ? ScannerUtils.readNullableString(scanner, prompt)
                        : ScannerUtils.readNonEmpty(scanner, prompt);

                if(line == null) return null;

                if(!ValidatorUtils.isValidEmail(line)) {
                    System.out.println("Invalid email format!");
                    continue;
                }

                if(customerDao.isDuplicateEmail(line, id)) {
                    System.out.println("Email already exists!");
                    continue;
                }
                return line;
            }
        } catch (SQLException e) {
            throw new ServiceException("Cannot check duplicate email! " + e.getMessage());
        }
    }

    private String readValidPhone (int id, String prompt, boolean isNullable) throws SQLException {
        try {
            String line;

            while (true) {
                line = isNullable
                        ? ScannerUtils.readNullableString(scanner, prompt)
                        : ScannerUtils.readNonEmpty(scanner, prompt);

                if(line == null) return null;

                if(!ValidatorUtils.isValidPhone(line)) {
                    System.out.println("Invalid phone format!");
                    continue;
                }

                if(customerDao.isDuplicatePhone(line, id)) {
                    System.out.println("Phone already exists!");
                    continue;
                }
                return line;
            }
        } catch (SQLException e) {
            throw new ServiceException("Cannot check duplicate phone! " + e.getMessage());
        }
    }
}
