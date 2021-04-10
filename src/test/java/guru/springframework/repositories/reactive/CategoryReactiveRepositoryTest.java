package guru.springframework.repositories.reactive;

import guru.springframework.domain.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Callable;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryTest {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Test
    public void before() throws Exception {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSave() throws Exception {
        Category category = new Category();
        category.setDescription("Foo");

        categoryReactiveRepository.save(category).block();

        Long count = categoryReactiveRepository.count().block();

        Assert.assertEquals(Long.valueOf(1l),count);
    }

    @Test
    public void FindByDescription() throws Exception {
        Category category = new Category();
        category.setDescription("Foo");

        categoryReactiveRepository.save(category).block();

        Category fetchCat = categoryReactiveRepository.findByDescription("Foo").block();

        Assert.assertNotNull(fetchCat.getId());

    }

}
