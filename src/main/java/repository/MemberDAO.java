package repository;

import java.util.ArrayList;
import domain.Member;

public interface MemberDAO {
	int create(Member m);
	Member read(Member m);
	ArrayList<Member> readList(Member m);
	int update(Member m);
	int delete(Member m);
}
