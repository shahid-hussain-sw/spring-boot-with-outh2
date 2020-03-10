package com.springjwt.service;

import com.springjwt.model.User;
import com.springjwt.repository.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserService implements UserDetailsService{


  @Autowired
  private UserRepository userRepository;


  public User save(User user) {
    return userRepository.save(user);
  }


  public List<User> findAll() {
    List<User> users = new ArrayList<>();
    userRepository.findAll().iterator().forEachRemaining(users::add);
    return users;
  }


  public void delete(long id) {
 //   userRepository.delete(id);

  }

  /**
   * Locates the user based on the username. In the actual implementation, the search may possibly
   * be case sensitive, or case insensitive depending on how the implementation instance is
   * configured. In this case, the <code>UserDetails</code> object that comes back may have a
   * username that is of a different case than what was actually requested..
   *
   * @param username the username identifying the user whose data is required.
   * @return a fully populated user record (never <code>null</code>)
   * @throws UsernameNotFoundException if the user could not be found or the user has no
   * GrantedAuthority
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userRepository.findByUsername(username);

    Optional.ofNullable(user).orElseThrow(()->new UsernameNotFoundException("Invalid username or password"));

    return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getAuthority());

  }

  private List<SimpleGrantedAuthority> getAuthority(){
    return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
  }
}
