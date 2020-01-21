package ru.nukkit.multiperms.util;

import ru.nukkit.multiperms.permissions.BaseNode;

import java.util.Comparator;

public class LowBase implements Comparator<BaseNode> {
    @Override
    public int compare(BaseNode o1, BaseNode o2) {
        if (o1.equals(o2)) return 0;
        return o1.getPriority() > o2.getPriority() ? 1 : -1;
    }
}
