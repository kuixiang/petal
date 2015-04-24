$(document).ready(function() {
    $('#external-events div.external-event').each(function() {
        var eventObject = {
            id:$(this).data('id'),
            title: $.trim($(this).text()) // use the element's text as the event title
        };

        // store the Event Object in the DOM element so we can get to it later
        $(this).data('eventObject', eventObject);

        // make the event draggable using jQuery UI
        $(this).draggable({
            zIndex: 999,
            revert: true,      // will cause the event to go back to its
            revertDuration: 0  //  original position after the drag
        });

    });
    $('#external-events div.external-event').on('click',function(){
        var eventObject=$(this).data('eventObject');
        var form = $("<form class='form-inline' action='/renderEvent/save.ajax'><label>改变事件标题 &nbsp;</label></form>");
        form.append("<input hidden name='id' value='"+eventObject.id+"'/>")
        form.append("<input class='middle' name='content' autocomplete=off type=text value='" + eventObject.title + "' /> ");
        form.append("<button type='submit' class='btn btn-sm btn-success'><i class='icon-ok'></i> 保存</button>");

        var div = bootbox.dialog({
            message: form,
            buttons: {
                "delete" : {
                    "label" : "<i class='icon-trash'></i> 删除",
                    "className" : "btn-sm btn-danger",
                    "callback": function() {
                        calendar.fullCalendar('removeEvents' , function(ev){
                            $.ajax({
                                url: "/renderEvent/delete.ajax",
                                type: "POST",
                                data: {
                                    id: eventObject.id
                                },
                                dataType: "json"
                            }).done(function (msg) {

                            }).fail(function (jqXHR, textStatus) {
                                alert("Request failed: " + textStatus);
                            });
                            return true;
                        })
                    }
                } ,
                "close" : {
                    "label" : "<i class='icon-remove'></i> 关闭",
                    "className" : "btn-sm"
                }
            }

        });
        form.submit(function() {
            calEvent.title = form.find("input[type=text]").val();
            $(this).ajaxSubmit(function() {
                calendar.fullCalendar('updateEvent', calEvent);
                div.modal("hide");
            });
            return false;
        });
    })
    // age is now ready, initialize the calendar...
    var calendar = $('#calendar').fullCalendar({
        buttonText: {
            prev: '<i class="icon-chevron-left"></i>',
            next: '<i class="icon-chevron-right"></i>'
        },

        header: {
            left: 'prev,next,today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        eventSources: [
            {
                url: '/calendar/calendarJob/listByUid.ajax',
                type: 'POST',
                data:{

                },
                error: function() {
                    alert('there was an error while fetching events!');
                },
                color: 'yellow',   // a non-ajax option
                textColor: 'black', // a non-ajax option
                className: 'label-success'
            }
            // any other sources...
        ],
        events: function(start,end, callback) {
            $.ajax({
                url:'/calendar/calendarJob/listByUid.ajax',
                dataType: 'json',
                data: {
                    start:Math.round(start.getTime() / 1000),
                    end: Math.round(end.getTime()/ 1000)
                },
                success:function(doc) {
                    var events =new Array();
                    $.each(doc,function(index) {
                        var $element=doc[index];
                        events.push({
                            id:$element.id,
                            title: $element.renderEvent.content,
                            className: 'label-success',
                            start: new Date($element.startDay),
                            end: new Date($element.endDay),
                            reid: $element.renderEvent.id
                        });
                    });
                    callback(events);
                }
            });
        },
        editable: true,
        droppable: true, // this allows things to be dropped onto the calendar !!!
        drop: function(date, allDay) { // this function is called when something is dropped

            // retrieve the dropped element's stored Event Object
            var originalEventObject = $(this).data('eventObject');
            var $extraEventClass = $(this).attr('data-class');


            // we need to copy it, so that multiple events don't have a reference to the same object
            var copiedEventObject = $.extend({}, originalEventObject);

            // assign it the date that was reported
            copiedEventObject.start = date;
            copiedEventObject.allDay = allDay;
            if($extraEventClass) copiedEventObject['className'] = [$extraEventClass];

            // render the event on the calendar
            // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
            $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);

            // is the "remove after drop" checkbox checked?
            if ($('#drop-remove').is(':checked')) {
                // if so, remove the element from the "Draggable Events" list
                $(this).remove();
            }

        }
        ,
        selectable: true,
        selectHelper: true,
        select: function(start, end, allDay) {
            bootbox.prompt("新建 待办事情:", function(title) {
                //1:建立一个事情
                if (title !== null) {
                    $.ajax({
                        url: "/renderEvent/save.ajax",
                        type: "POST",
                        data: {
                            content : title
                        },
                        dataType: "json"
                    }).done(function( renderEvent ) {
                        //2：建立一个日程
                        if (title !== null) {
                            $.ajax({
                                url: "/calendar/save.ajax",
                                type: "POST",
                                data: {
                                    startDay: start,
                                    endDay: end,
                                    reid: renderEvent.id
                                },
                                dataType: "json"
                            }).done(function (msg) {
                                var calendarObj = {
                                    title: title,
                                    start: start,
                                    end: end,
                                    allDay: allDay
                                }
                                calendar.fullCalendar('renderEvent', calendarObj, true);
                            }).fail(function (jqXHR, textStatus) {
                                alert("Request failed: " + textStatus);
                            });
                        }
                    }).fail(function( jqXHR, textStatus ) {
                        alert( "Request failed: " + textStatus );
                    });

                }
            });
            calendar.fullCalendar('unselect');
        }
        ,
        eventClick: function(calEvent, jsEvent, view) {
            var form = $("<form class='form-inline' action='/renderEvent/save.ajax'><label>改变事件标题 &nbsp;</label></form>");
            form.append("<input hidden name='id' value='"+calEvent.reid+"'/>")
            form.append("<input class='middle' name='content' autocomplete=off type=text value='" + calEvent.title + "' /> ");
            form.append("<button type='submit' class='btn btn-sm btn-success'><i class='icon-ok'></i> 保存</button>");

            var div = bootbox.dialog({
                message: form,
                buttons: {
                    "delete" : {
                        "label" : "<i class='icon-trash'></i> 删除",
                        "className" : "btn-sm btn-danger",
                        "callback": function() {
                            calendar.fullCalendar('removeEvents' , function(ev){
                                $.ajax({
                                    url: "/calendar/delete.ajax",
                                    type: "POST",
                                    data: {
                                        id: calEvent.id
                                    },
                                    dataType: "json"
                                }).done(function (msg) {

                                }).fail(function (jqXHR, textStatus) {
                                    alert("Request failed: " + textStatus);
                                });
                                return (ev._id == calEvent._id);
                            })
                        }
                    } ,
                    "close" : {
                        "label" : "<i class='icon-remove'></i> 关闭",
                        "className" : "btn-sm"
                    }
                }

            });
            form.submit(function() {
                calEvent.title = form.find("input[type=text]").val();
                $(this).ajaxSubmit(function() {
                    calendar.fullCalendar('updateEvent', calEvent);
                    div.modal("hide");
                });
                return false;
            });
        }

    });

});