package ru.nukkit.multiperms.permissions;

import java.util.Collection;

public class Permission {

    private final String name;
    private final boolean positive;
    private Collection<? extends Permission> permissions;


    public Permission(String name, boolean positive) {
        this.name = name;
        this.positive = positive;
    }

    public Permission(String name) {
        positive = !(name.startsWith("-"));
        this.name = this.positive ? name : name.replaceFirst("-", "");
    }

    public String getName() {
        return this.name;
    }

    public boolean isPositive() {
        return this.positive;
    }

    public String toString() {
        return positive ? name : new StringBuilder("-").append(name).toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return name != null && name.equalsIgnoreCase(that.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }


}
