package com.game.miniCivilization;

import com.game.miniCivilization.domain.City;
import com.game.miniCivilization.domain.Tile;
import com.game.miniCivilization.domain.Unit;
import com.game.miniCivilization.repository.CityRepo;
import com.game.miniCivilization.repository.MainRepo;
import com.game.miniCivilization.repository.UnitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MainService {
//    @Autowired
    private final MainRepo mainRepo;
    @Autowired
    private CityRepo cityRepo;
    @Autowired
    private UnitRepo unitRepo;

    public MainService(MainRepo mainRepo) {
        this.mainRepo = mainRepo;
    }

    public Iterable<Tile> findAllTiles(){
        return mainRepo.findAll();
    }

    public Tile findOnebyId(Long id){
        return mainRepo.findById(id)
            .orElseThrow();
    }

    public Tile saveTile(Tile tile){
        return mainRepo.save(tile);
    }
// its 100% working without argument
    public void moveUnit(Long tileIdStart, Long tileIdEnd){
        Tile tempStart = mainRepo.findById(tileIdStart).get();
        Tile tempEnd = mainRepo.findById(tileIdEnd).get();
        tempEnd.setUnit(tempStart.getUnit());
        tempStart.setUnit(null);
        mainRepo.save(tempEnd);
        mainRepo.save(tempStart);
    }

    public void creatCity(Long id){
        Tile temp = mainRepo.findById(id).get();
        City tempCity = new City();
        tempCity.setName(1+"");
        cityRepo.save(tempCity);
        temp.setCity(tempCity);
        mainRepo.save(temp);
    }

    public void creatUnit(Long id) {
        Tile tile = mainRepo.findById(id).get();
        Unit unit = new Unit();
        unit.setId(2);
        unit.setName(2+"");
        unitRepo.save(unit);
        tile.setUnit(unit);
        mainRepo.save(tile);
    }
}
