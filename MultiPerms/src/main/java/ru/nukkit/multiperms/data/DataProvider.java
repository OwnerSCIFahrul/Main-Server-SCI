package ru.nukkit.multiperms.data;

import ru.nukkit.multiperms.permissions.Group;
import ru.nukkit.multiperms.permissions.Groups;
import ru.nukkit.multiperms.permissions.User;
import ru.nukkit.multiperms.permissions.Users;

import java.util.Collection;

public abstract class DataProvider {

    public abstract void saveUser(User user);

    public abstract User loadUser(String playerName);

    public abstract void removeUser(String playerName);

    public abstract void saveGroups(Collection<Group> all);

    public abstract Collection<Group> loadGroups();

    public abstract boolean isStored(String userName);

    public abstract Collection<User> getAllUsers();

    public void updateUser(final User user) {
        Users.setUser(new User(user.getName(), user));
    }

    public void updateAllGroups(final Collection<Group> groups) {
        Groups.updateGroups(groups);
    }

    public abstract boolean isEnabled();

    public abstract void clearUsers();

    public abstract void clearGroups();

    public abstract void saveUsers(Collection<User> users);

    public abstract void saveGroup(Group group);

    public abstract void removeGroup(String groupId);
}
