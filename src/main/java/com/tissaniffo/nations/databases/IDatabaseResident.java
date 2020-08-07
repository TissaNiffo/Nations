package com.tissaniffo.nations.databases;

import com.tissaniffo.nations.object.Resident;
import com.tissaniffo.nations.object.Town;

public interface IDatabaseResident {

    Boolean createResident(Resident resident, Town town);

    Boolean existsResident(Resident resident);

    Boolean saveResident(Resident resident);

}
