package bikestores.services;

public interface IService <T>{

    T getByID (int id);

    int insert ();

    T update();

    boolean delete();
}
