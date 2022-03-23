package com.kjy.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

// Dao 라는 것을 명시해줌
// DAO: 데이터베이스에 접근해서 DML 쿼리(SELECT, INSERT, UPDATE, DELETE)를 실행하는 메서드의 모음.
@Dao
interface RoomMemoDao {
    // 삽입, 조회, 수정, 삭제에 해당하는 3개의 메서드를 만들고 각각의 어노테이션을 붙여줌.
    // 다른 ORM 툴과 다르게 조회를 하는 SELECT 쿼리는 직접 작성하도록 설계되어 있음.
    // 대부분의 ORM은 SELECT도 메서드 형태로 제공
    @Query("select * from room_memo")
    fun getAll(): List<RoomMemo>

    // 옵션으로 OnConflict = REPLACE를 적용하면 동일한 키를 가진 값이 입력되었을 때 UPDATE 쿼리로 실행.
    @Insert(onConflict = REPLACE)
    fun insert(memo: RoomMemo)

    @Delete
    fun delete(memo: RoomMemo)

}