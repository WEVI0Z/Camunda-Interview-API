package by.wevioz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ErrorDto {
    private List<String> messageKeys;
    private Exception exception;
}