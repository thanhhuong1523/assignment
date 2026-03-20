package bikestores.services;

import bikestores.dao.StoreDao;
import bikestores.dto.StoreDto;
import bikestores.exceptions.ServiceException;
import bikestores.models.Store;
import bikestores.utils.ScannerUtils;
import bikestores.utils.ValidatorUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class StoreService implements IService<Store> {
    private final Scanner scanner;
    private final StoreDao storeDao;

    public StoreService (Scanner scanner, Connection conn) {
        this.scanner = scanner;
        storeDao = new StoreDao(conn);
    }

    public ArrayList<Store> selectAll (String name, String address, String phone) {
        try {
            return storeDao.selectAll(name, address, phone);
        } catch (SQLException e) {
            throw new ServiceException("Cannot get all stores: " + e.getMessage());
        }
    }

    @Override
    public Store getByID(int id) {
        try {
            Store store = storeDao.getByID(id);

            if(store == null) {
                throw new ServiceException("ID not found!");
            } else return store;

        } catch (SQLException e) {
            throw new ServiceException("Cannot find this store: " + e.getMessage());
        }
    }

    @Override
    public int insert() {
        try {
            StoreDto storeDto = createStoreDtoForInsert(-1);
            return storeDao.insert(storeDto);

        } catch (SQLException e) {
            throw new ServiceException("Cannot insert new store: " + e.getMessage());
        }
    }

    @Override
    public Store update() {
        try {
            int id = getID();
            Store oldStore = getByID(id);
            StoreDto storeDto = createStoreDtoForUpdate(id);

            if(storeDto.getName() == null) {
                storeDto.setName(oldStore.getName());
            } else {
                oldStore.setName(storeDto.getName());
            }

            if(storeDto.getAddress() == null) {
                storeDto.setAddress(oldStore.getAddress());
            } else {
                oldStore.setAddress(storeDto.getAddress());
            }

            if(storeDto.getPhone() == null) {
                storeDto.setPhone(oldStore.getPhone());
            } else {
                oldStore.setPhone(storeDto.getPhone());
            }

            return storeDao.update(id, storeDto) ? oldStore : null;

        } catch (SQLException e) {
            throw new ServiceException("Cannot update this store: " + e.getMessage());
        }
    }

    @Override
    public boolean delete() {
        try {
            int id = getID();
            Store store = getByID(id);

            if(storeDao.isReferencedByOtherTables(id)) {
                throw new ServiceException("Cannot delete store with existing staff or stock!");
            }

            return storeDao.delete(id);
        } catch (SQLException e) {
            throw new ServiceException("Cannot delete this store: " + e.getMessage());
        }
    }

    private int getID() {
        return ScannerUtils.readPositiveInt(scanner, "Enter store ID: ", false);
    }

    private StoreDto createStoreDtoForInsert (int id) throws SQLException {
        String name = readValidName(id, "Enter store's name: ", false);
        String address = readValidAddress(id, "Enter store's address: ", false);
        String phone = readValidPhone("Enter store's phone: ", false);

        return new StoreDto(name, address, phone);
    }

    private StoreDto createStoreDtoForUpdate (int id) throws SQLException {
        String name = readValidName(id, "Enter store's name (blank to skip): ", true);
        String address = readValidAddress(id, "Enter store's address (blank to skip): ", true);
        String phone = readValidPhone("Enter store's phone (blank to skip): ", true);

        return new StoreDto(name, address, phone);
    }

    private String readValidName(int id, String prompt, boolean isNullable) {
        try {
            String line;

            while (true) {
                line = isNullable
                        ? ScannerUtils.readNullableString(scanner, prompt)
                        : ScannerUtils.readNonEmpty(scanner, prompt);

                if(line == null) return null;

                if(storeDao.isDuplicateName(line, id)) {
                    System.out.println("Name already exists!");
                    continue;
                }
                return line;
            }
        } catch (SQLException e) {
            throw new ServiceException("Cannot check duplicate name: " + e.getMessage());
        }
    }

    private String readValidPhone (String prompt, boolean isNullable) {
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
            return line;
        }
    }

    private String readValidAddress (int id, String prompt, boolean isNullable) {
        try {
            String line;

            while (true) {
                line = isNullable
                        ? ScannerUtils.readNullableString(scanner, prompt)
                        : ScannerUtils.readNonEmpty(scanner, prompt);

                if(line == null) return null;

                if(storeDao.isDuplicateAddress(line, id)) {
                    System.out.println("Address already exists!");
                    continue;
                }
                return line;
            }
        } catch (SQLException e) {
            throw new ServiceException("Cannot check duplicate address: "+ e.getMessage());
        }
    }
}
