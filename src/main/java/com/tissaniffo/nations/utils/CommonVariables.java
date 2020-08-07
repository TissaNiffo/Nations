package com.tissaniffo.nations.utils;

import com.tissaniffo.nations.databases.IDatabaseResident;
import com.tissaniffo.nations.databases.IDatabaseTown;
import com.tissaniffo.nations.object.Resident;
import com.tissaniffo.nations.object.Town;
import org.bukkit.entity.Player;

import java.util.Map;

class CommonVariables {
    Map<String, Resident> residents;
    Map<String, Town> towns;

    IDatabaseResident residentData;
    IDatabaseTown  townData;
}
