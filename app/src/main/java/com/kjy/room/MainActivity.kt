package com.kjy.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.kjy.room.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    // RoomHelper 변수 설정.
    var helper: RoomHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // helper 생성
        // name 부분이 실제 DB 파일의 이름.
        // Room은 기본적으로 서브 스레드에서 동작되도록 설계 되어 있어서 allowMainThreadQueries() 옵션이 적용되지
        // 않으면 앱이 동작을 멈춤.
        helper = Room.databaseBuilder(this, RoomHelper::class.java, "room_memo")
            .allowMainThreadQueries()
            .build()

        // RecyclerAdapter를 생성
        val adapter = RecyclerAdapter()

        // helper를 어댑터에 전달해줌
        adapter.helper = helper

        // adapter의 데이터 목록을 RoomHelper를 사용하는 것으로 변경.
        // helper에 null이 허용되기 때문에 helper안의 코드 사용을 위해서 helper?.의 형태로 사용
        // roomMemoDao 동일, adapter의 listData에 null이 허용되지 않기 때문에 마지막에 elvis연산자로
        // 앞의 2개가 null일 경우 사용하기 위한 Default값 설정.
        adapter.listData.addAll(helper?.roomMemoDao()?.getAll()?: listOf())

        // 화면의 리사이클러뷰 위젯에 adapter를 연결하고 레이아웃 매니저를 설정
        binding.recyclerMemo.adapter = adapter
        binding.recyclerMemo.layoutManager = LinearLayoutManager(this)

        // 저장 버튼에 클릭리스너를 연결
        binding.buttonSave.setOnClickListener {
            // 메모를 입력하는 플레인텍스트를 검사해서 값이 있으면 해당 내용으로 RoomMemo 클래스를 생성
            if (binding.editMemo.text.toString().isNotEmpty()) {
                val memo = RoomMemo(binding.editMemo.text.toString(), System.currentTimeMillis())

                helper?.roomMemoDao()?.insert(memo)

                // 어댑터 데이터 초기화
                adapter.listData.clear()

                // 데이터베이스에서 새로운 목록을 읽어와 어댑터에 세팅하고 갱신함.
                adapter.listData.addAll(helper?.roomMemoDao()?.getAll()?: listOf())
                // 새로 생성되는 메모에는 번호가 자동 입력되므로 번호를 갱신하기 위해서 새로운 데이터를 셋팅
                adapter.notifyDataSetChanged()

                // 메모 내용을 입력하는 위젯의 내용을 지워서 초기화
                binding.editMemo.setText("")
            }
        }


    }
}