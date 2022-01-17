package web_crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web_crud.dao.UserDao;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;


    @Autowired
    public UserDetailsServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    // «Пользователь» – это просто Object. В большинстве случаев он может быть
    //  приведен к классу UserDetails.
    // Для создания UserDetails используется интерфейс UserDetailsService, с единственным методом:
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        web_crud.model.User user = userDao.findByUserName(name);
        if(user == null){
            throw  new UsernameNotFoundException("User name is null");
        }
        return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }
}
