package com.gun2.restest.controller.rest;

import com.gun2.restest.component.scheduler.SchedulerComponent;
import com.gun2.restest.component.scheduler.SchedulerInfo;
import com.gun2.restest.constant.SuccessCode;
import com.gun2.restest.dto.HttpResponseDto;
import com.gun2.restest.dto.SchedulerStateDto;
import com.gun2.restest.form.response.SuccessResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@MessageMapping("/scheduler")
@RequestMapping("/api")
public class SchedulerRestController {

    private final SimpMessageSendingOperations sendingOperations;
    private final SchedulerComponent schedulerComponent;

    public SchedulerRestController(SimpMessageSendingOperations sendingOperations, @Lazy SchedulerComponent schedulerComponent) {
        this.sendingOperations = sendingOperations;
        this.schedulerComponent = schedulerComponent;
    }

    @MessageMapping("/init")
    public void init(String msg){
        List<SchedulerStateDto> schedulerStateDtoList =  schedulerComponent
                .getSchedulerInfoMap()
                .values()
                .stream()
                .map(info -> SchedulerStateDto.of(info)).toList();
        sendingOperations.convertAndSend("/scheduler/init", schedulerStateDtoList);
    }

    public void deleteScheduleInfo(Long id){
        sendingOperations.convertAndSend("/scheduler/delete", id);
    }

    public void insertScheduleInfo(SchedulerInfo schedulerInfo){
        sendingOperations.convertAndSend("/scheduler/insert", SchedulerStateDto.of(schedulerInfo));
    }

    public void updateScheduleInfo(SchedulerInfo schedulerInfo){
        sendingOperations.convertAndSend("/scheduler/update", SchedulerStateDto.of(schedulerInfo));
    }

    public void pushFailedResponse(Long scheduleId, HttpResponseDto httpResponseDto){
        sendingOperations.convertAndSend("/scheduler/"+scheduleId+"/failure", httpResponseDto);
    }
    public void pushSucceedResponse(Long scheduleId, HttpResponseDto httpResponseDto){
        sendingOperations.convertAndSend("/scheduler/"+scheduleId+"/success", httpResponseDto);
    }


    /**
     * <p>스케줄id에 해당하는 스케줄러가 실패한 내역 조회</p>
     * @param id
     * @return
     */
    @GetMapping("/v1/scheduler/{id}/responses/failures")
    @ResponseBody
    public ResponseEntity<List<HttpResponseDto>> findFailedResponses(@PathVariable("id") Long id){
        SchedulerInfo schedulerInfo = schedulerComponent.getSchedulerInfoMap().get(id);
        return SuccessResponse.of(schedulerInfo.getFailureResponseList().toList()).toResponseEntity(SuccessCode.OK);
    }

    /**
     * <p>스케줄id에 해당하는 스케줄러가 성공한 내역 조회</p>
     * @param id
     * @return
     */
    @GetMapping("/v1/scheduler/{id}/responses/successes")
    @ResponseBody
    public ResponseEntity<List<HttpResponseDto>> findSucceedResponses(@PathVariable("id") Long id){
        SchedulerInfo schedulerInfo = schedulerComponent.getSchedulerInfoMap().get(id);
        return SuccessResponse.of(schedulerInfo.getSuccessResponseList().toList()).toResponseEntity(SuccessCode.OK);
    }

}
