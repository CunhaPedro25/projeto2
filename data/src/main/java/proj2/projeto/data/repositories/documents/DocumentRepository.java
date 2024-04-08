package proj2.projeto.data.repositories.documents;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface DocumentRepository<T> extends JpaRepository<T, Long> {
  T findById(Integer id);

  @Query("SELECT e FROM #{#entityName} e WHERE e.client = ?1")
  List<T> findDocumentsByClient_Id(Integer id);

  @Query("SELECT e FROM #{#entityName} e WHERE e.engineer = ?1")
  List<T> findDocumentsByEngineer_Id(Integer id);

  Optional<T> findByFilePath(String file_path);

  Optional<T> findByCreateDate(LocalDate date);

//  @Query("SELECT e FROM #{#entityName} e WHERE e.create_date = ?1")
//  List<T> findDocumentsByCreateDate(LocalDate date);

  @Query("SELECT e FROM #{#entityName} e WHERE e.accepted = true")
  List<T> findAcceptedDocuments();

  @Query("SELECT e FROM #{#entityName} e WHERE e.accepted = false")
  List<T> findDeclinedDocuments();
}
