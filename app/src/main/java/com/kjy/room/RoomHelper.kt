package com.kjy.room

import androidx.room.Database
import androidx.room.RoomDatabase


// 추상클래스로 RoomHelper 클래스를 생성하고 RoomDatabase를 상속 받음.
// @Database 어노테이션으로 데이터베이션 설정
// 옵션 1: entities = Room 라이브러리가 사용할 엔터티(테이블) 클래스 목록
// 옵션 2: version = 데이터베이스의 버전
// 옵션 3: exportSchema = true면 스키마 정보를 파일로 출력
@Database(entities = arrayOf(RoomMemo::class), version = 1, exportSchema = false)
abstract class RoomHelper: RoomDatabase() {
    abstract fun roomMemoDao(): RoomMemoDao


}