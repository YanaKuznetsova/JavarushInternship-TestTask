package testtask;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ItemRepository extends PagingAndSortingRepository<Item, Integer> {

    Page<Item> findByNecessity(Integer necessity, Pageable pageable);
    Page<Item> findByName(String name, Pageable pageable);
    Page<Item> findByNameAndNecessity(String name, Integer necessity, Pageable pageable);
    List<Item> findByNecessity(Integer necessity);

    @Transactional
    void deleteById(Integer id);

}
