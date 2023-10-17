package com.zied.bankingApp.repositories;

import com.zied.bankingApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    // select * from _user where firstName = 'Zied';
     List<User> findAllByFirstName(String firstName);

    // select * from _user where firstName like '%Zied%'
     List<User> findAllByFirstNameContaining(String firstName);

    // select * from _user where firstName ilike '%Zied%'
     List<User> findAllByFirstNameContainingIgnoreCase(String firstName);

    // select * from _user u inner join account a on u.id = a.user_id and a.iban = 'QRY444545';
    List<User> findAllByAccountIban(String iban);

    // select * from _user where firstName = '%Zied%' and email='zied@gmail.com';
    User findUserByFirstNameContainingIgnoreCaseAndEmail(String firstName, String email);

    //---------------------JPQL -- entity name, not table's name---------------------------------------------
    // In JPQL we manipulate entities, not SQL tables
    @Query("from User where firstName = :fn")
    List<User> searchByFirstName(@Param("fn") String firstName);

    @Query("from User where firstName = '%:firstName%'")
    List<User> searchByFirstNameContaining(String firstName);

    @Query("from User u inner join Account a on u.id = a.user.id where a.iban = :iban")
    List<User> searchByIBan(String iban);

    //---------------------Query - native SQL -------------------------------------------------------------
    @Query(value = "select * from _user u inner join Account a on u.id = a.user.id where a.iban = :iban",nativeQuery = true)
    List<User> searchByIBanNative(String iban);


}
