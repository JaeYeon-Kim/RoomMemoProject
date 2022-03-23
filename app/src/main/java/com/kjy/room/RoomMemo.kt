package com.kjy.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Room라이브러리는 @Entity 어노테이션이 적용된 클래스를 찾아 테이블로 변환함.
// 형식 @Entity(tableName = "테이블명") 으로 작성.
@Entity (tableName = "room_memo")
class RoomMemo {
    // 멤버 변수 no, content, date 선언
    // 변수명 위에 @ColumnInfo 어노테이션을 작성해서 테이블의 컬럼으로 사용함.
    // 컬럼명도 테이블명처럼 변수명과 다르게 하고 싶을 때는 @ColumnInfo(name = "컬럼명") 으로 설정.

    // no 변수에는 @PrimaryKey 어노테이션을 사용해서 키(Key)라는 점을 명시하고 자동 증가 옵션을 추가한다.
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var no: Long? = null

    @ColumnInfo
    var content: String = ""

    @ColumnInfo(name = "date")
    var datetime: Long = 0

    // content와 datetime을 받는 생성자를 작성
    constructor(content: String, datetime: Long) {
        this.content = content
        this.datetime = datetime
    }

}