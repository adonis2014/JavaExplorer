package name.chenyuelin.dao.mapping;

import java.util.List;
import name.chenyuelin.model.Person;
import name.chenyuelin.model.PersonCondition;
import org.apache.ibatis.annotations.Param;

public interface PersonMapper {
    int countByExample(PersonCondition example);

    int deleteByExample(PersonCondition example);

    int deleteByPrimaryKey(Byte id);

    int insert(Person record);

    int insertSelective(Person record);

    List<Person> selectByExampleWithBLOBs(PersonCondition example);

    List<Person> selectByExample(PersonCondition example);

    Person selectByPrimaryKey(Byte id);

    int updateByExampleSelective(@Param("record") Person record, @Param("example") PersonCondition example);

    int updateByExampleWithBLOBs(@Param("record") Person record, @Param("example") PersonCondition example);

    int updateByExample(@Param("record") Person record, @Param("example") PersonCondition example);

    int updateByPrimaryKeySelective(Person record);

    int updateByPrimaryKeyWithBLOBs(Person record);

    int updateByPrimaryKey(Person record);
}