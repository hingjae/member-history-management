# 사용자 이력 관리

### 개발 배경

- 운영 환경의 사용자가 미승인 상태로 바뀌는 현상이 발생.
- 생성/수정자 생성/수정 일시는 관리되지만 이전 데이터 이력을 확인할 수 없음.
- 관계형 DB 레코드의 히스토리를 관리 하는 방법에 대한 고민.

### Spring-Data-Envers

- JPA와 통합해서 사용하는 라이브러리.
- 엔티티의 생성, 수정, 삭제를 자동으로 추적하고, 히스토리 테이블에 저장.
- 엔티티 과거 이력 조회 기능 제공.

### rev 테이블

```sql
CREATE TABLE `revinfo` (
   `rev` BIGINT AUTO_INCREMENT NOT NULL,
   `revtstmp` BIGINT NULL,
   PRIMARY KEY (`rev`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

- envers가 자동으로 관리하는 테이블
- 엔티티 변경이 생길 때마다 메타데이터를 기록
- 기본 컬럼 : 리비전 식별자, 타임스탬프
- `@RevisionEntity` 로 커스텀 가능

### history 테이블

```sql
CREATE TABLE `member_aud` (
    `id` VARCHAR(36) NOT NULL,      -- 엔티티 기본 키
    `rev` BIGINT NOT NULL,          -- 리비전 번호 (revinfo 테이블에서 참조됨)
    `revtype` TINYINT NOT NULL,     -- 리비전 유형: 0(추가), 1(수정), 2(삭제)

    `name` VARCHAR(100) NOT NULL,   -- 감사(audit)할 컬럼.
    `age` INT NOT NULL,             -- 감사(audit)할 컬럼.

    PRIMARY KEY (`id`, `rev`),      -- 복합키: 엔티티 ID와 리비전 ID
    CONSTRAINT `fk_member_aud_revinfo` 
        FOREIGN KEY (`rev`) 
        REFERENCES `revinfo`(`rev`) -- revinfo 테이블과의 외래키 관계
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

- envers가 자동으로 관리하는 테이블
- 등록, 수정, 삭제가 발생할 때 마다 히스토리를 쌓는 방식
- 이름 규칙
  - `table명_aud`
  - ex) member -> member_aud
- revtype
  - 등록(0), 수정(1), 삭제(2)를 식별하는 컬럼
- 기본키는 엔티티의 기본키와 리비전의 기본키를 결합한 복합키 전략을 사용

### Entity

```java
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Member {

    @Id
    private String id;

    @Audited
    private String name;

    @Audited
    private int age;

    private String phoneNumber;
}
```

- 감사(audit)할 필드에 `@Audited` 붙이기 
- `@Audited` 필드가 수정됐을 때 envers가 리비전(변경내용)을 히스토리 테이블에 저장함.

---
### AuditReader()

- Hibernate Envers에서 제공하는 인터페이스
- 리비전을 조회하는 쿼리를 만들 때 사용한다.
- 조건, 정렬, 페이징 지원

### RevisionRepository
```java
@NoRepositoryBean
public interface RevisionRepository<T, ID, N extends Number & Comparable<N>> extends Repository<T, ID> {
    Optional<Revision<N, T>> findLastChangeRevision(ID id);

    Revisions<N, T> findRevisions(ID id);

    Page<Revision<N, T>> findRevisions(ID id, Pageable pageable);

    Optional<Revision<N, T>> findRevision(ID id, N revisionNumber);
}
```
- Spring Data Envers 에서 제공하는 인터페이스
- 엔티티 히스토리를 편리하게 조회할 수 있는 기본적인 메서드를 제공.
- 조건, 정렬, 페이징 지원