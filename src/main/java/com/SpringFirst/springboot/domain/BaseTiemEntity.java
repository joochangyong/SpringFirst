package com.SpringFirst.springboot.domain;
//BaseTiemEntity클래스는 모든 Entity의 상위 클래스가 되어 Entity들의 createdDate, modifiedDate를 자동으로 관리하는 역할.

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //JPA Entity 클래스들이 BaseTimeEntity을 상속할 경우 필드들(createdDate, modifiedDdate)도 칼럼으로 인식하도록 함.
@EntityListeners(AuditingEntityListener.class) //BaseTimeEntity클래스에 Audting기능을 호함.
public class BaseTiemEntity {

    @CreatedDate //Entity가 생성되어 자장될 때 시간이 자동 저장.
    private LocalDateTime createdDate;

    @LastModifiedDate //조회한 Entity의 값을 변경할 때 시간이 자동 저장.
    private LocalDateTime modifiedDate;
}
