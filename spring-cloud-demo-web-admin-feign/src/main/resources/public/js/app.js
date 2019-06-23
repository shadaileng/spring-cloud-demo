const APP = function () {
    let defaultOptions = {
        deleteUrl: 'delete',
        getUserUrl: "user",
        updateUrl: 'update'
    }

    let initCheck = function () {
        // console.log('init iCheck')

        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' /* optional */
        });
        let _checkMaster = $('input[type="checkbox"].minimal.check-master')
        let _checkBox = $('input[type="checkbox"].minimal')
        $('input[type="checkbox"].minimal.check-master').on('ifClicked', function (e) {
            // 取消
            if(e.target.checked) {
                _checkBox.iCheck('uncheck')
            }
            // 选中
            else {
                _checkBox.iCheck('check')
            }
        })
    }

    let getCheckedId = function() {
        let ids = []
        $('input[type="checkbox"].minimal:checked').each((i, e) => {
            ids.push(e.dataset.id)
        })
        return ids
    }
    
    let updateById = function(id) {
        if (id === null || id === undefined || id === '') return
        let loadingIndex
        $.ajax({
            url: defaultOptions.getUserUrl + id,
            type: 'GET',
            beforeSend: function () {
                loadingIndex = layer.msg('处理中', {icon: 16})
            },
            success: function (result) {
                layer.close(loadingIndex)
                if (result.result) {
                    console.log('查询成功: ')
                    let _data = result.data
                    ModalUtil.init({
                        data: _data
                    })
                    ModalUtil.switch("show")
                } else {
                    layer.open({content: result.message, icon: 5, shift: 6}, function () {

                    })
                }
            },
            error: function () {
                layer.close(loadingIndex)
                console.log('查询失败')
                layer.open({content: '处理异常', icon: 5, shift: 6}, function () {

                })
            }
        })
        
    }

    let deleteByIds = function (ids) {
        if (ids.length <= 0) {
            layer.open({content: '请至少选择一条记录删除', icon: 5, shift: 6}, function () {

            })
            return
        }
        layer.confirm('是否删除[' + ids + ']?', {icon: 3, title: '删除'}, function(index) {
            let loadingIndex;
            $.ajax({
                url: defaultOptions.deleteUrl,
                type: 'POST',
                data: {ids: ids.toString()},
                beforeSend: function () {
                    loadingIndex = layer.msg('处理中', {icon: 16})
                },
                success: function (result) {
                    layer.close(loadingIndex);
                    if(result.result) {
                        $('#modal-default').modal('hide')
                        layer.msg(result.message, {time:2000, icon: 6, shift: 6}, function () {
                            TableUtils.firstPage() && window.location.reload()
                            // window.location.reload()
                        })
                    } else {
                        layer.open({content: result.message, icon: 5, shift: 6}, function () {

                        })
                    }
                },
                error: function () {
                    layer.close(loadingIndex)
                    layer.open({content: "处理异常", icon: 5, shift: 6}, function () {

                    })
                }
            })
        })
    }
    let _extends = function (src, target) {
        for (let i in src) {
            let val = src[i]
            target[i] = val
        }
        return target
    }

    return {
        initCheck: function () {
            initCheck()
        },
        setParam: function(opts) {
            _extends(opts, defaultOptions)
        },
        initComponents: function () {
            initCheck()
            let $upd = $('button[name=upd]')
            $upd.off('click')
            $upd.on('click', function (e) {
                let id = e.target.dataset.id
                updateById(id)
            })
            let $del = $('button[name=del]')
            $del.off('click')
            $del.on('click', function (e) {
                let ids = e.target.dataset.id
                deleteByIds([ids]);
            })
            let $delBatch = $('#batchDel')
            $delBatch.off('click')
            $delBatch.on('click', function (e) {
                let ids = getCheckedId()
                deleteByIds(ids);
            })
        }

    }
}();

const ModalUtil = function() {
    let defaultOptions = {
        data: {},
        target: '#modalId',
        title: {
            update: '更新',
            insert: '新增'
        },
        url: {
            save: 'save'
        },
        callback: function () {
            console.log("callback...")
        },
        init: function () {
            console.log("init...")
        }
    }

    let setFormData = function (data, formId) {
        let names = []
        document.querySelectorAll(formId + ' [name]').forEach(e=>{
            names.push(e.name)
        })
        names = [...new Set(names)]
        for (let i = 0, l = names.length; i < l; i++)
            // for (let key in data)
            {
                key = names[i]
            // console.log(key, data[key])
            let tag = $(formId).find('[name="' + key + '"]');
            if (tag.length > 1) {
                tag.prop('checked', false);
                if(data[key]) {
                    let item = data[key].split(',');
                    item.forEach((el, i) => {
                        let e = $(formId).find('[name="' + key + '"][value="' + el + '"]');
                        e.prop('checked', true)
                    })
                }
            } else {
                let val
                if (key.lastIndexOf(".") > 0) {
                    val = data
                    key.split(".").forEach(el => {
                        if (val) {
                            val = val[el]
                        }
                    })
                } else {
                    val = data[key]
                }
                tag.val(val)
            }
        }
    }

    let _extends = function (src, target) {
        for (let i in src) {
            let val = src[i]
            target[i] = val
        }
        return target
    }
    /**
     * 模态框初始化
     * @param modalId
     * @param titleUpdate
     * @param titleAdd
     */
    let initModal = function (opts) {
        opts = _extends(opts, defaultOptions)
        let {target, title, data, url} = opts

        /**
         * modal显示时回调处理
         */
        $(target).on('show.bs.modal', (e)=>{
            // let id = e.relatedTarget.getAttribute('data-id')
            // let loadingIndex;
            let _title = ''
            setFormData(defaultOptions.data, target + " form")
            document.querySelector(target + ' .modal-title').innerHTML = _title
        })


        /**
         * modal模态框被隐藏后处理
         */
        $(target).on('hidden.bs.modal', (e)=>{
            setFormData(data, target + " form")
            defaultOptions.init && defaultOptions.init()
        })

        /**
         * 确定按钮事件
         */
        $(target + ' .modal-footer .btn-primary').off('click')
        $(target + ' .modal-footer .btn-primary').on('click', e => {
            console.log("save...")
            let loadingIndex;
            let id = document.querySelector(target + ' form [name="id"]').value
            let uri = url.save
            if (!layer) {
                console.log("layer 未加载")
                return
            }
            $.ajax({
                url: uri,
                type: 'POST',
                data: $('#inputForm').serialize(),
                beforeSend: function () {
                    loadingIndex = layer.msg('处理中', {icon: 16})
                },
                success: function (result) {
                    layer.close(loadingIndex);
                    if(result.result) {
                        $('#modal-default').modal('hide')
                        layer.msg(result.message, {time:2000, icon: 6, shift: 6}, function () {
                            defaultOptions.callback && defaultOptions.callback()
                            // TableUtils.firstPage()
                            // console.log("reflash")
                            // window.location.reload()
                        })
                    } else {
                        layer.open({content: '_' + result.message, icon: 5, shift: 6}, function () {

                        })
                    }
                },
                error: function () {
                    layer.close(loadingIndex)
                    layer.open({content: "处理异常", icon: 5, shift: 6}, function () {

                    })
                }
            })
        })
    }

    return {
        init: function (opts) {
            initModal(opts)
        },
        switch: function (opt) {
            if(opt.length <= 0) {
                (opt = 'toggle')
            }
            ['hide', 'show', 'toggle'].forEach(e => {
                if (e === opt) {
                    $(defaultOptions.target).modal(opt)
                }
            })
        }
    }
}()

var DropzoneUtil = function() {
    let defaultOptions = {
        dropz: null,
        target: '#dropz',
        url: "/upload", // 文件提交地址
        method: "post",  // 也可用put
        paramName: 'dropzFile', // 默认为file
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        //previewsContainer:"#preview", // 上传图片的预览窗口
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传1个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消",
        addedFile: function(file, data) {
            // console.log('addedFile', file, data)
        },
        success: function(file, data) {
            // console.log('success', file, data)
        },
        error: function(file, data) {
            // console.log('error', file, data)
        },
        removedfile: function(file, data) {
            // console.log('removedfile', data)
        },
        init: function () {
            this.on("addedfile", defaultOptions.addedFile);
            this.on("success", defaultOptions.success);
            this.on("error", defaultOptions.error);
            this.on("removedfile", defaultOptions.removedfile);
        }
    }

    let _extends = function (src, target) {
        for (let i in src) {
            let val = src[i]
            target[i] = val
        }
        return target
    }
    let init = function (opts) {
        opts = _extends(opts, defaultOptions)
        // 关闭 Dropzone 的自动发现功能
        Dropzone.autoDiscover = false
        defaultOptions.dropz = new Dropzone(opts.target, opts)
    }
    return {
        init: function (opts) {
            init(opts)
        },
        dropz: function () {
            return defaultOptions.dropz
        }
    }
}()

var EditorUtil = function() {
   let defaultOptions = {
       editor: null,
       target: '#editor',
       url: '',
       uploadImgShowBase64: false,
       uploadFileName: 'editorFile',
       uploadImgHooks: null,
       onchange: null,
       onblur: null,
       uploadImgTimeout: 5000,
       uploadImgMaxSize: 5 * 1024 * 1024
   }
    let _extends = function (src, target) {
        for (let i in src) {
            let val = src[i]
            target[i] = val
        }
        return target
    }
   let init = function (opts) {
       opts = _extends(opts, defaultOptions)

       let Editor = window.wangEditor
       let editor = new Editor('#editor')


       // 使用 base64 保存图片
       editor.customConfig.uploadImgShowBase64 = opts.uploadImgShowBase64
       if (!opts.uploadImgShowBase64 && opts.url.length > 0) {
           editor.customConfig.uploadImgServer = opts.url
       }
       // 隐藏“网络图片”tab
       // editor.customConfig.showLinkImg = false
       // 自定义上传文件参数名
       editor.customConfig.uploadFileName = opts.uploadFileName
       editor.customConfig.uploadImgHooks = opts.uploadImgHooks
       editor.customConfig.onchange = opts.onchange
       editor.customConfig.onblur = opts.onblur
       // 将 timeout 时间改为 3s
       // editor.customConfig.uploadImgTimeout = 3000
       // 将图片大小限制为 3M,默认限制图片大小是 5M
       // editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024

       editor.create()
       defaultOptions.editor = editor
   }
   return {
       init: function(opts) {
           init(opts)
       },
       editor: function () {
           return defaultOptions.editor
       }
   }
}()

$(function () {
    if(!layer) {
        console.log("layer 未加载...")
        return
    }
    if(!jQuery) {
        console.log("jQuery 未加载...")
        return
    }
    console.log("加载 app")
    APP.initCheck()
    APP.initComponents()
})