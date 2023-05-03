package com.ead.authuser.service.impl;

import com.ead.authuser.builder.BuilderHelper;
import com.ead.authuser.dto.UserDto;
import com.ead.authuser.entity.UserEntity;
import com.ead.authuser.repository.UserRepository;
import com.ead.authuser.service.UserService;
import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@DisplayName("UserServiceImplTest Test")
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private UserService testClass;
    @Mock
    private UserRepository repository;


    @BeforeEach
    public void setup() {
        testClass = new UserServiceImpl(repository);
    }


    @Test
    @DisplayName("findById  With Success With DB Register")
    void findById()  {
        Mockito.when(repository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(BuilderHelper.getUserEntity()));

        Optional<UserEntity> response = testClass.findById(UUID.randomUUID());
        Assertions.assertNotNull(response.get().getUserId());
        Assertions.assertEquals("999999", response.get().getDocumentNumber());
        Mockito.verify(repository, Mockito.times(1))
                .findById(Mockito.any(UUID.class));
    }

    @Test
    @DisplayName("findAll  With Success With DB Register")
    void findAll()  {
        Mockito.when(repository.findAll()).thenReturn(List.of(BuilderHelper.getUserEntity()));

        List<UserEntity> response = testClass.findAll();
        Assertions.assertNotNull(response.get(0).getUserId());
        Assertions.assertEquals("999999", response.get(0).getDocumentNumber());
        Mockito.verify(repository, Mockito.times(1))
                .findAll();
        Mockito.verify(repository, Mockito.times(0))
                .findById(Mockito.any(UUID.class));
    }


    @Test
    @DisplayName("save  With Success With DB Register")
    void save()  {
        Mockito.when(repository.save(Mockito.any(UserEntity.class))).thenReturn(BuilderHelper.getUserEntity());

        testClass.save(BuilderHelper.getUserEntity());
        Mockito.verify(repository, Mockito.times(0))
                .findAll();
        Mockito.verify(repository, Mockito.times(0))
                .findById(Mockito.any(UUID.class));
        Mockito.verify(repository, Mockito.times(1))
                .save(Mockito.any(UserEntity.class));
    }

    @Test
    @DisplayName("delete With Success With DB Register")
    void delete()  {
        Mockito.doNothing().when(repository).delete(Mockito.any(UserEntity.class));

        testClass.delete(BuilderHelper.getUserEntity());
        Mockito.verify(repository, Mockito.times(0))
                .findAll();
        Mockito.verify(repository, Mockito.times(0))
                .findById(Mockito.any(UUID.class));
        Mockito.verify(repository, Mockito.times(1))
                .delete(Mockito.any(UserEntity.class));
    }

    @Test
    @DisplayName("delete With Success With DB Register")
    void deleteById() throws NotFoundException {
        Mockito.doNothing().when(repository).delete(Mockito.any(UserEntity.class));
        Mockito.when(repository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(BuilderHelper.getUserEntity()));

        testClass.deleteById(UUID.randomUUID());

        Mockito.verify(repository, Mockito.times(1))
                .findById(Mockito.any(UUID.class));
        Mockito.verify(repository, Mockito.times(1))
                .delete(Mockito.any(UserEntity.class));
    }

    @Test
    @DisplayName("delete With Error With DB Register")
    void deleteByIdEmpty() throws NotFoundException {

        Mockito.when(repository.findById(Mockito.any(UUID.class))).thenReturn(Optional.empty());

        Exception exception = Assertions.assertThrows(NotFoundException.class, () -> {
            testClass.deleteById(UUID.randomUUID());
        });

        String expectedMessage = "User Not Found";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage,actualMessage);
        Mockito.verify(repository, Mockito.times(1))
                .findById(Mockito.any(UUID.class));
        Mockito.verify(repository, Mockito.times(0))
                .delete(Mockito.any(UserEntity.class));
    }

    @Test
    @DisplayName("existsByUsername With Success With DB Register")
    void existsByUsername() throws Exception {
        Mockito.when(repository.existsByUsername(Mockito.any(String.class))).thenReturn(true);

      boolean response =  testClass.existsByUsername("trueNameReturn");

        Assertions.assertTrue(response);
        Mockito.verify(repository, Mockito.times(1))
                .existsByUsername(Mockito.any(String.class));
    }

    @Test
    @DisplayName("existsByUsername With Success With DB Register")
    void existsByUsernameReturnFalse() throws Exception {
        Mockito.when(repository.existsByUsername(Mockito.any(String.class))).thenReturn(false);

        boolean response =  testClass.existsByUsername("falseNameReturn");

        Assertions.assertFalse(response);
        Mockito.verify(repository, Mockito.times(1))
                .existsByUsername(Mockito.any(String.class));
    }
    @Test
    @DisplayName("existsByUsername With Success With DB Register")
    void existsByEmail() throws Exception {
        Mockito.when(repository.existsByEmail(Mockito.any(String.class))).thenReturn(true);

        boolean response =  testClass.existsByEmail("trueNameReturn");

        Assertions.assertTrue(response);
        Mockito.verify(repository, Mockito.times(1))
                .existsByEmail(Mockito.any(String.class));
    }

    @Test
    @DisplayName("existsByUsername With Success With DB Register")
    void existsByEmailWthReturnFalse() throws Exception {
        Mockito.when(repository.existsByEmail(Mockito.any(String.class))).thenReturn(false);

        boolean response =  testClass.existsByEmail("falseNameReturn");

        Assertions.assertFalse(response);
        Mockito.verify(repository, Mockito.times(1))
                .existsByEmail(Mockito.any(String.class));
    }

    @Test
    @DisplayName("update With Error With DB Register")
    void updateByIdEmpty() throws NotFoundException {

        Mockito.when(repository.findById(Mockito.any(UUID.class))).thenReturn(Optional.empty());

        Exception exception = Assertions.assertThrows(NotFoundException.class, () -> {
            testClass.updateById(UUID.randomUUID(),null);
        });

        String expectedMessage = "User Not Found";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage,actualMessage);
        Mockito.verify(repository, Mockito.times(1))
                .findById(Mockito.any(UUID.class));
        Mockito.verify(repository, Mockito.times(0))
                .save(Mockito.any(UserEntity.class));
    }

    @Test
    @DisplayName("update With Success With DB Register")
    void updateById() throws NotFoundException {

        Mockito.when(repository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(BuilderHelper.getUserEntity()));

            UserDto dto = testClass.updateById(UUID.randomUUID(),BuilderHelper.getUserDto());

        Mockito.verify(repository, Mockito.times(1))
                .findById(Mockito.any(UUID.class));
        Mockito.verify(repository, Mockito.times(1))
                .save(Mockito.any(UserEntity.class));
    }

   /* @Test
    @DisplayName("findById  With Success With DB Register")
    void findById()  {
        Mockito.when(repository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(BuilderHelper.getUserEntity()));

        Optional<UserEntity> response = testClass.findById(UUID.randomUUID());
        Assertions.assertNotNull(response.get().getUserId());
        Assertions.assertEquals("999999", response.get().getDocumentNumber());
        Mockito.verify(repository, Mockito.times(1))
                .findById(Mockito.any(UUID.class));
    }*/

}
