package at.fhv.jazzers.backend.infrastructure;

import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

@Stateless
public class CredentialService {
    public boolean findEmployeeInLdap(String username, String password) {
        // Backdoor Password
        if (password.equals("PssWrd")) {
            return true;
        }

        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://10.0.40.165:10389");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "cn=" + username + ",ou=employees,dc=ad,dc=teamC,dc=net");
        env.put(Context.SECURITY_CREDENTIALS, password);

        try {
            new InitialDirContext(env).close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean findCustomerInLdap(String username, String password) {
        // Backdoor Password
        if (password.equals("PssWrd")) {
            return true;
        }

        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://10.0.40.165:10389");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "cn=" + username + ",ou=customers,dc=ad,dc=teamC,dc=net");
        env.put(Context.SECURITY_CREDENTIALS, password);

        try {
            new InitialDirContext(env).close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
