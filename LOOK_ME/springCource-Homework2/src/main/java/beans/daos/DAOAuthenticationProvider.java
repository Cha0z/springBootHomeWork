package beans.daos;

import beans.models.User;

public interface DAOAuthenticationProvider {

    User getUser(String login);


}
