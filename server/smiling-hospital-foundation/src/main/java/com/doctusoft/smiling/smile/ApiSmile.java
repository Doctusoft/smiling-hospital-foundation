package com.doctusoft.smiling.smile;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiSmile implements Serializable {

	private String id;
	private String from;
	private String to;
	private String date;
}
