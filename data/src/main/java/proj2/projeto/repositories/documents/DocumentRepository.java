package proj2.projeto.repositories.documents;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface DocumentRepository<T> extends JpaRepository<T, Long> {
  T findById(Integer id);

  @Query("SELECT e FROM #{#entityName} e WHERE e.client = ?1")
  List<T> findByClient_Id(Integer id);

  @Query("SELECT e FROM #{#entityName} e WHERE e.engineer = ?1")
  List<T> findByEngineer_Id(Integer id);

  @Query("SELECT e FROM #{#entityName} e WHERE e.file_path = ?1")
  List<T> findByFilePath(String file_path);

  @Query("SELECT e FROM #{#entityName} e WHERE e.create_date = ?1")
  List<T> findByDate(Date date);

  @Query("SELECT e FROM #{#entityName} e WHERE e.accepted = true")
  List<T> findAccepted();

  @Query("SELECT e FROM #{#entityName} e WHERE e.accepted = false")
  List<T> findDeclined();
}
