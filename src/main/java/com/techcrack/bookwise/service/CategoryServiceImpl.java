package com.techcrack.bookwise.service;

import com.techcrack.bookwise.abstractions.CategoryService;
import com.techcrack.bookwise.entity.Category;
import com.techcrack.bookwise.exceptions.customized.InvalidDataException;
import com.techcrack.bookwise.exceptions.customized.ObjectNotFoundException;
import com.techcrack.bookwise.abstractions.CurrentUserService;
import com.techcrack.bookwise.repository.CategoryRepository;
import com.techcrack.bookwise.utils.AbstractRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends AbstractRepository<CategoryServiceImpl, CategoryRepository>
                                implements CategoryService {

    public CategoryServiceImpl(CategoryRepository repo, CurrentUserService userSession) {
        super(CategoryServiceImpl.class, repo, userSession);
    }

    @Transactional
    public Category register(Category category) {
        logger.info("Category Registration Process started {}", category.getName());

        if (repo.existsByNameAndIsActiveTrue(category.getName())) {
            logger.error("Category already exists.  with details {}", category);
            throw new InvalidDataException("Category name must be unique.");
        }

        category = repo.save(category);

        logger.info("Category Registered successfully with {}", category.getName());
        return category;
    }

    @Override
    public void remove(long key) {
        repo.deleteById(key);
    }

    @Override
    public Category update(Category entity) {
        return repo.save(entity);
    }

    public List<Category> getAllCategories() {
        return repo.findAllByIsActiveTrue();
    }

    public void remove(String name) {
        logger.info("Category Remove Process started for {}", name);

        Category category = getCategoryByName(name);

        category.setActive(false);

        repo.save(category);

        logger.info("Category Removed successfully for {}", name);
    }

    public Category getCategoryByName(String name) throws ObjectNotFoundException {
        logger.info("Fetching Category by {}", name);

        Category category = repo.findByNameAndIsActive(name, true)
                .orElseThrow(() -> new ObjectNotFoundException(Category.class, "Category not found with : " + name));

        logger.info("Fetched category by {}", name);

        return category;
    }

    public Category get(long id) {
        logger.info("Fetching Category By Id {}", id);

        return repo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(Category.class, "Category not found with Id : " + id));
    }
}
