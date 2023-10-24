package com.hamony.boot

import com.hamony.boot.dto.MemberDTO
import com.hamony.boot.entity.Diary
import com.hamony.boot.entity.DiaryTag
import com.hamony.boot.entity.Member
import com.hamony.boot.entity.Tag
import com.hamony.boot.repository.DiaryRepository
import com.hamony.boot.repository.DiaryTagRepository
import com.hamony.boot.repository.MemberRepository
import com.hamony.boot.repository.TagRepository
import com.hamony.boot.service.MemberService
import jakarta.transaction.Transactional
import org.hibernate.annotations.NaturalId
import org.junit.jupiter.api.Test
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class BootApplicationTests {

    @Autowired
    open lateinit var memberRepository: MemberRepository

    @Autowired
    open lateinit var diaryRepository: DiaryRepository

    @Autowired
    open lateinit var tagRepository: TagRepository

    @Autowired
    open lateinit var diaryTagRepository: DiaryTagRepository

    @Autowired
    lateinit var memberService: MemberService

    @Autowired
    lateinit var modelMapper: ModelMapper

    @Test
    @Transactional
    fun memberWrite() {

        var member: Member = Member(null,"sdf","sdf","sdf", LocalDateTime.now(),null);
        var member2: Member = Member(null,"sdf2","sdf2","sdf2", LocalDateTime.now(),null);
        memberRepository.save(member)
        memberRepository.save(member2)
        memberRepository.findAll().forEach(::println);

        var diary = Diary(null,"123","123", LocalDateTime.now(),null,null, member)
        var diary1 = Diary(null,"1234","1234", LocalDateTime.now(),null,null, member)
        var tag = Tag(null, "TEST")
        var diaryTag = DiaryTag(null, diary, tag)
        var diaryTag1 = DiaryTag(null, diary1, tag)
        diaryRepository.save(diary)
        tagRepository.save(tag)
        diaryTagRepository.save(diaryTag)
        diaryTagRepository.save(diaryTag1)

//        println("----------------")
//        diaryRepository.findAll().forEach(::println)
//        println("----------------")
//        memberRepository.findAll().forEach(::println)
//
//        println("----------------")
//        diaryRepository.findAllByMember(member).forEach(::println)
//        println("----------------")
//        diaryTagRepository.findAll().forEach(::println)
//        println("----------------")
//        diaryTagRepository.findAllByDiary(diary).forEach(::println)
//        println("----------------")
//
//        tagRepository.findAll().forEach(::println)
    }


    @Test
    fun modelmapper(){
//        val member: Member = memberRepository.findByUserId("test123").get()
//        println(modelMapper.map(member, MemberDTO::class.java))

    }
}
