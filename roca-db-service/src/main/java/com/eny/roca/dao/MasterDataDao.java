package com.eny.roca.dao;

import java.util.List;

import com.eny.roca.db.bean.MasterData;

public interface MasterDataDao {

	List<MasterData> getMasterData(String string);

}
