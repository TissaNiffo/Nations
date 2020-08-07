package com.tissaniffo.nations.databases;

import com.tissaniffo.nations.object.Resident;
import com.tissaniffo.nations.object.Town;

public interface IDatabaseTown {

    Boolean createTown(Town town, String name, Resident major);

    Boolean existsTown(Town town);

    Boolean saveTown(Town town);

    Boolean loadTowns();

}
