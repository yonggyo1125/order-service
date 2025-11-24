package org.spartahub.orderservice.domain;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record DeadLineMessage(
    UUID orderNo,
    String ordererName,
    String ordererEmail,
    double estimateTime,
    LocalDateTime orderDate,
    String orderProducts,
    String orderMemo,
    String startHub,
    String stopoverHub,
    String arrivalAddress,
    String staffName,
    String staffEmail
) {}
