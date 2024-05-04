package com.luka.r18.controller;

import com.luka.r18.entity.ModelEntity;
import com.luka.r18.entity.response_object.ResponseCodeEntity;
import com.luka.r18.service.ModelService;
import com.luka.r18.util.CustomUtil;
import com.luka.r18.util.TokenUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * (Model)表控制层
 *
 * @author Luka
 * @since 2024-02-05 04:26:19
 */
@RestController
@RequestMapping("model")
public class ModelController {
    /**
     * 服务对象
     */
    @Resource
    private ModelService modelService;

    @PostMapping
    public ResponseEntity<ResponseCodeEntity<List<ModelEntity>>> model(HttpServletRequest request) {
        String token = request.getHeader("token");
        String username = TokenUtil.getTokenClaim(token, "username");
        if (username == null) {
            ResponseCodeEntity<List<ModelEntity>> token1 = new ResponseCodeEntity<>(400, "Token无效", null);
            return ResponseEntity.ok(token1);
        }
        List<ModelEntity> modelEntities = modelService.queryByUserName(username);

        return ResponseEntity.ok(new ResponseCodeEntity<List<ModelEntity>>(400, "Token无效", modelEntities));
    }

}

