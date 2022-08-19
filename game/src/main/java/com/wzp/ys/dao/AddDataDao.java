package com.wzp.ys.dao;

import com.wzp.ys.model.Echats;
import com.wzp.ys.model.Relics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddDataDao {

    List<Relics> selectRelics(Relics data);

    List<Relics> selectRelicsName();

    void insertRelics(@Param("Relics") List<Relics> list);

    List<Echats> selectEm();
}
