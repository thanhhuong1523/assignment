package bikestores.services;

import bikestores.dao.StaffDao;
import bikestores.dto.StaffDto;
import bikestores.exceptions.ServiceException;
import bikestores.models.Staff;
import bikestores.utils.ScannerUtils;
import bikestores.utils.ValidatorUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class StaffService implements IService <Staff> {
    private final Scanner sc;
    private final StaffDao staffDao;

    public StaffService(Scanner sc, Connection conn) {
        this.sc = sc;
        staffDao = new StaffDao(conn);
    }

    public ArrayList<Staff> selectAll(String name, String role, int storeID) {
        try {
            return staffDao.selectAll(name, role, storeID);
        } catch (SQLException e) {
            throw new ServiceException("Cannot get all staffs: " + e.getMessage());
        }
    }

    @Override
    public Staff getByID(int id) {
        try {
            Staff staff = staffDao.getByID(id);

            if(staff == null) {
                throw new ServiceException("ID not found!");
            } else return staff;

        } catch (SQLException e) {
            throw new ServiceException("Cannot find this staff by ID! " + e.getMessage());
        }
    }

    @Override
    public int insert() {
        try {
            StaffDto staffDto = createStaffDtoForInsert(-1);
            return staffDao.insert(staffDto);

        } catch (SQLException e) {
            throw new ServiceException("Cannot insert this staff: " + e.getMessage());
        }
    }

    @Override
    public Staff update() {
        try {
            int id = getID();
            Staff oldStaff = getByID(id);

            StaffDto staffDto = createStaffDtoForUpdate(id);

            if(staffDto.getName() == null) {
                staffDto.setName(oldStaff.getName());
            } else {
                oldStaff.setName(staffDto.getName());
            }

            if(staffDto.getRole() == null) {
                staffDto.setRole(oldStaff.getRole());
            } else {
                oldStaff.setRole(staffDto.getRole());
            }

            if(staffDto.getEmail() == null) {
                staffDto.setEmail(oldStaff.getEmail());
            } else {
                oldStaff.setEmail(staffDto.getEmail());
            }

            if(staffDto.getPhone() == null) {
                oldStaff.setPhone(staffDto.getPhone());
            } else {
                oldStaff.setPhone(staffDto.getPhone());
            }

            if(staffDto.getStoreID() == -1) {
                staffDto.setStoreID(oldStaff.getStoreID());
            } else {
                oldStaff.setStoreID(staffDto.getStoreID());
            }

            return staffDao.update(id, staffDto) ? oldStaff : null;

        } catch (SQLException e) {
            throw new ServiceException("Cannot update this staff: " + e.getMessage());
        }
    }

    @Override
    public boolean delete() {
        try {
            int id = getID();
            Staff staff = getByID(id);

            return staffDao.delete(id);

        } catch (SQLException e) {
            throw new ServiceException("Cannot delete this staff: " + e.getMessage());
        }
    }

    private int getID() {
        return ScannerUtils.readPositiveInt(sc, "Enter staff ID: ", false);
    }

    private StaffDto createStaffDtoForInsert(int id) throws SQLException {
        String name = ScannerUtils.readNonEmpty(sc, "Enter staff's name: ");
        String role = ScannerUtils.readNonEmpty(sc, "Enter staff's role: ").toLowerCase(Locale.ROOT);
        String email = readValidEmail(id, "Enter email: ", false);
        String phone = readValidPhone(id, "Enter phone: ", false);
        int storeID = readValidStoreID("Enter storeID of this staff: ", false);

        return new StaffDto(name, role, email, phone, storeID);
    }

    private StaffDto createStaffDtoForUpdate(int id) throws SQLException {
        String name = ScannerUtils.readNullableString(sc, "Enter name (blank to skip): ");
        String role = ScannerUtils.readNullableString(sc, "Enter role (blank to skip): ");
        String email = readValidEmail(id, "Enter email (blank to skip): ", true);
        String phone = readValidPhone(id, "Enter phone (blank to skip): ", true);
        int storeID = readValidStoreID( "Enter store ID (blank to skip): ", true);

        return new StaffDto(name, role, email, phone, storeID);
    }

    private String readValidEmail(int id, String prompt, boolean isNullable) {
        try {
            String line;

            while (true) {
                line = isNullable
                        ? ScannerUtils.readNullableString(sc, prompt)
                        : ScannerUtils.readNonEmpty(sc, prompt);

                if(line == null) return null;

                if(!ValidatorUtils.isValidEmail(line)) {
                    System.out.println("Invalid email format!");
                    continue;
                }

                if(staffDao.isDuplicateEmail(line, id)) {
                    System.out.println("Email already exists!");
                    continue;
                }
                return line;
            }
        } catch (SQLException e) {
            throw new ServiceException("Cannot check duplicate email: " + e.getMessage());
        }
    }

    private String readValidPhone(int id, String prompt, boolean isNullable) {
        try {
            String line;

            while (true) {
                line = isNullable ?
                        ScannerUtils.readNullableString(sc, prompt)
                        : ScannerUtils.readNonEmpty(sc, prompt);

                if(line == null) return null;

                if(!ValidatorUtils.isValidPhone(line)) {
                    System.out.println("Invalid phone format!");
                    continue;
                }

                if(staffDao.isDuplicatePhone(line, id)) {
                    System.out.println("Phone already exists!");
                    continue;
                }

                return line;
            }
        } catch (SQLException e) {
            throw new ServiceException("Cannot check duplicate phone: " + e.getMessage());
        }
    }

    private int readValidStoreID(String prompt, boolean isNullable) {
        int storeID;
        try {
            while (true) {
                storeID = ScannerUtils.readPositiveInt(sc, prompt, isNullable);

                if(!staffDao.isExistedStoreID(storeID)) {
                    System.out.println("Store ID not found!");
                    continue;
                }
                return storeID;
            }
        } catch (SQLException e) {
            throw new ServiceException("Cannot check existed store ID " + e.getMessage());
        }
    }
}
