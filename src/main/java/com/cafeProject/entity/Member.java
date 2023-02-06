package com.cafeProject.entity;

import javax.persistence.*;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.cafeProject.constant.Role;
import com.cafeProject.dto.MemberDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "member") // 테이블명
@Getter
@Setter
@ToString
public class Member {
	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	private String email;
	
	
	private String password;
	
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public static Member createMember(MemberDto memberDto, PasswordEncoder passwordEncoder) {
		Member member = new Member();
		member.setEmail(memberDto.getEmail());
		member.setName(memberDto.getName());
		
		String password = passwordEncoder.encode(memberDto.getPassword());
		member.setPassword(password);
		
		member.setRole(Role.USER);
		
		return member;
	}
	
}
