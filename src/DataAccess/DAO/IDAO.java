package DataAccess.DAO;

import java.util.List;

public interface IDAO<T> {
    public T readBy(Integer id) throws Exception;

    public List<T> readAll() throws Exception;

    public boolean create(T entity) throws Exception;

    public boolean update(T entity) throws Exception;

    public boolean delete(Integer id) throws Exception;
    // public Integer getMaxRow() throws Exception;
}
