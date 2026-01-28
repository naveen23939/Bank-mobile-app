package com.bank.acconts.dto;

import com.bank.acconts.entity.Account;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;
@ConfigurationProperties(prefix = "accounts")
public record AccountContactInfoDto(String message, Map<String, String> contactDetails, List<String> onCallSupport) {
}
