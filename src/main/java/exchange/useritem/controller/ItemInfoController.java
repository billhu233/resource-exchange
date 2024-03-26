package exchange.useritem.controller;

import exchange.common.page.PageResult;
import exchange.common.result.R;
import exchange.useritem.domain.vo.req.SelectMyItemRequest;
import exchange.useritem.domain.vo.req.SelectUserItemRequest;
import exchange.useritem.domain.vo.req.UserItemRequest;
import exchange.useritem.domain.vo.resp.UserItemResponse;
import exchange.useritem.manager.UserItemManager;
import exchange.useritem.mapper.ItemInfoMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 物品信息表 前端控制器
 * </p>
 *
 * @author hb
 * @since 2024-02-29
 */
@RestController
@RequestMapping("/itemInfo")
public class ItemInfoController {

    private UserItemManager userItemManager;

    private ItemInfoMapper itemInfoMapper;

    /**
     * 列表查询
     */
    @PostMapping("/selectMyItemPage")
    public R<PageResult<UserItemResponse>> getMyItemPage(@RequestBody SelectMyItemRequest request) {
        return R.success(itemInfoMapper.getMyItems(request));
    }

    /**
     * 列表查询
     */
    @PostMapping("/selectItemPage")
    public R<PageResult<UserItemResponse>> getPage(@RequestBody SelectUserItemRequest request) {
        return R.success(itemInfoMapper.getPage(request));
    }

    /**
     * 上传文件
     */
    @PostMapping("/uploadItem")
    public R<Boolean> uploadFile(@RequestParam("file") MultipartFile file, @RequestBody UserItemRequest request) {
        return R.success(userItemManager.uploadFile(file, request));
    }
}
