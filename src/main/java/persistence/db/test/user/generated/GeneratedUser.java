package persistence.db.test.user.generated;

import com.speedment.common.annotation.GeneratedCode;
import com.speedment.runtime.config.identifier.ColumnIdentifier;
import com.speedment.runtime.config.identifier.TableIdentifier;
import com.speedment.runtime.field.StringField;
import com.speedment.runtime.typemapper.TypeMapper;
import persistence.db.test.user.User;

/**
 * The generated base for the {@link persistence.db.test.user.User}-interface
 * representing entities of the {@code User}-table in the database.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made to
 * it will be overwritten.
 * 
 * @author Speedment
 */
@GeneratedCode("Speedment")
public interface GeneratedUser {
    
    /**
     * This Field corresponds to the {@link User} field that can be obtained
     * using the {@link User#getUserId()} method.
     */
    StringField<User, String> USER_ID = StringField.create(
        Identifier.USER_ID,
        User::getUserId,
        User::setUserId,
        TypeMapper.identity(),
        true
    );
    /**
     * This Field corresponds to the {@link User} field that can be obtained
     * using the {@link User#getName()} method.
     */
    StringField<User, String> NAME = StringField.create(
        Identifier.NAME,
        User::getName,
        User::setName,
        TypeMapper.identity(),
        false
    );
    /**
     * This Field corresponds to the {@link User} field that can be obtained
     * using the {@link User#getPassword()} method.
     */
    StringField<User, String> PASSWORD = StringField.create(
        Identifier.PASSWORD,
        User::getPassword,
        User::setPassword,
        TypeMapper.identity(),
        false
    );
    /**
     * This Field corresponds to the {@link User} field that can be obtained
     * using the {@link User#getEmail()} method.
     */
    StringField<User, String> EMAIL = StringField.create(
        Identifier.EMAIL,
        User::getEmail,
        User::setEmail,
        TypeMapper.identity(),
        false
    );
    
    /**
     * Returns the userId of this User. The userId field corresponds to the
     * database column test.test.User.UserId.
     * 
     * @return the userId of this User
     */
    String getUserId();
    
    /**
     * Returns the name of this User. The name field corresponds to the database
     * column test.test.User.Name.
     * 
     * @return the name of this User
     */
    String getName();
    
    /**
     * Returns the password of this User. The password field corresponds to the
     * database column test.test.User.Password.
     * 
     * @return the password of this User
     */
    String getPassword();
    
    /**
     * Returns the email of this User. The email field corresponds to the
     * database column test.test.User.Email.
     * 
     * @return the email of this User
     */
    String getEmail();
    
    /**
     * Sets the userId of this User. The userId field corresponds to the
     * database column test.test.User.UserId.
     * 
     * @param userId to set of this User
     * @return       this User instance
     */
    User setUserId(String userId);
    
    /**
     * Sets the name of this User. The name field corresponds to the database
     * column test.test.User.Name.
     * 
     * @param name to set of this User
     * @return     this User instance
     */
    User setName(String name);
    
    /**
     * Sets the password of this User. The password field corresponds to the
     * database column test.test.User.Password.
     * 
     * @param password to set of this User
     * @return         this User instance
     */
    User setPassword(String password);
    
    /**
     * Sets the email of this User. The email field corresponds to the database
     * column test.test.User.Email.
     * 
     * @param email to set of this User
     * @return      this User instance
     */
    User setEmail(String email);
    
    enum Identifier implements ColumnIdentifier<User> {
        
        USER_ID  ("UserId"),
        NAME     ("Name"),
        PASSWORD ("Password"),
        EMAIL    ("Email");
        
        private final String columnId;
        private final TableIdentifier<User> tableIdentifier;
        
        Identifier(String columnId) {
            this.columnId        = columnId;
            this.tableIdentifier = TableIdentifier.of(    getDbmsId(), 
                getSchemaId(), 
                getTableId());
        }
        
        @Override
        public String getDbmsId() {
            return "test";
        }
        
        @Override
        public String getSchemaId() {
            return "test";
        }
        
        @Override
        public String getTableId() {
            return "User";
        }
        
        @Override
        public String getColumnId() {
            return this.columnId;
        }
        
        @Override
        public TableIdentifier<User> asTableIdentifier() {
            return this.tableIdentifier;
        }
    }
}