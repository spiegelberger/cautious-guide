package com.spiegelberger.springit.domain;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.ocpsoft.prettytime.PrettyTime;

import com.spiegelberger.springit.service.BeanUtil;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Comment extends Auditable{
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NonNull
	private String body;
	
	@NonNull
	@ManyToOne
	@ToString.Exclude
	private Link link;
	
	/*
	 * Pretty Time instead of plain dates
	 */
	public String getPrettyTime() {
	    PrettyTime pt = BeanUtil.getBean(PrettyTime.class);
	    return pt.format(convertToDateViaInstant(getCreationDate()));
	}

	private Date convertToDateViaInstant(LocalDateTime dateToConvert) {
	    return java.util.Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
	}

}
