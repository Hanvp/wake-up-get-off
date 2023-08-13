package practice.web_app.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    //@Api(tags = "Root")
    @ApiOperation(value = "서버 상태 확인", notes = "I'm healthy!")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성곰"),
            @ApiResponse(code = 401, message = "접근 권한이 없습니다.")
    })
    @GetMapping("/health")
    public String healthAPI() {
        return "잘 작동중";
    }
}
