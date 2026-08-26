(function() {
  var openCollapseIfNeeded, playbackStarted,
    __slice = [].slice;

  $(document).ready(function() {

      $(".hover").hover(function(){
        $(this).attr("fill", "#FF4F00");
      },
      function(){
        $(this).attr("fill", "#FF7A01");
      });

    return $(document).on("click", "code", selectText);
  });

  this.togglePictures = function() {
    var event, i, idx, img, path, paths, src, _i, _len;
    event = arguments[0], paths = 2 <= arguments.length ? __slice.call(arguments, 1) : [];
    img = $(event.target).closest("img");
    src = img.attr("src");
    i = -1;
    for (idx = _i = 0, _len = paths.length; _i < _len; idx = ++_i) {
      path = paths[idx];
      if (path === src) {
        i = idx;
      }
    }
    img.attr("src", paths[(i + 1) % paths.length]);
    return stopPropagation(event);
  };

  this.selectText = function(event) {
    var range, target;
    target = event.target;
    if (document.selection) {
      range = document.body.createTextRange();
      range.moveToElementText(target);
      range.select();
    } else if (window.getSelection) {
      range = document.createRange();
      range.selectNode(target);
      var sel = window.getSelection();
      sel.removeAllRanges();
      sel.addRange(range);
    }
  };

  this.stopPropagation = function(event) {
    if (event) {
      event.cancelBubble = true;
    }
    if (event && event.stopPropagation) {
      event.stopPropagation();
    }
    if (event && event.preventDefault) {
      event.preventDefault();
    }
    return false;
  };


  this.initCollapse = function(collapse, control, label) {
    $(collapse).on("shown.bs.collapse", function() {
      return $(control).html('Закрыть <i class="fa fa-angle-double-up"></i>');
    });
    $(collapse).on("hidden.bs.collapse", function() {
      var btn, tgt;
      btn = $(control);
      btn.html(label + ' <i class="fa fa-angle-double-down"></i>');
      tgt = btn[0];
      if (tgt.scrollIntoViewIfNeeded) {
        return tgt.scrollIntoViewIfNeeded();
      } else {
        return tgt.scrollIntoView();
      }
    });
    return openCollapseIfNeeded(collapse);
  };

  openCollapseIfNeeded = function(collapse) {
    var anchors;
    if (window.location.hash) {
      anchors = $(collapse).children("a[name='" + window.location.hash.substring(1) + "']");
      if (anchors.length) {
        $(collapse).collapse('show');
        return anchors[0].scrollIntoView();
      }
    }
  };

  this.initVideo = function(collapse) {
    return $(collapse).on("show.bs.collapse", function() {
      return $(collapse).find("iframe").each(function() {
        if ($(this).attr("src") === "") {
          return $(this).attr("src", $(this).attr("data-src"));
        }
      });
    });
  };

  this.initModal = function(hash) {
    if (window.location.hash === hash) {
      $(hash).modal("show");
    }
    return $(hash).find("a").each(function() {
      if (!$(this).attr("target")) {
        return $(this).click(function(event) {
          var ref;
          ref = $(this).attr("href");
          $(hash).modal("hide");
          setTimeout((function() {
            return window.location.replace(ref);
          }), 350);
          return stopPropagation(event);
        });
      }
    });
  };

  playbackStarted = -1;

  this.initAudio = function() {
    $("audio").on("ended", function() {
      var all, au, audio, cur, i, next, _i, _len;
      $(this).removeClass("chosen");
      all = $("audio");
      for (i = _i = 0, _len = all.length; _i < _len; i = ++_i) {
        au = all[i];
        if (au === this) {
          cur = i;
        }
      }
      if (playbackStarted === -1) {
        playbackStarted = cur;
      }
      next = (cur + 1) % all.length;
      if (next === playbackStarted) {
        playbackStarted = -1;
      } else {
        audio = all[next];
        audio.volume = this.volume;
        audio.play();
        if (audio.scrollIntoViewIfNeeded) {
          audio.scrollIntoViewIfNeeded();
        } else {
          audio.scrollIntoView();
        }
      }
      return true;
    });
    $("audio").on("pause", function() {
      return $(this).removeClass("chosen");
    });
    return $("audio").on("play", function() {
      var au, _i, _len, _ref;
      $(this).addClass("chosen");
      _ref = $("audio");
      for (_i = 0, _len = _ref.length; _i < _len; _i++) {
        au = _ref[_i];
        if (!(au !== this && !au.paused)) {
          continue;
        }
        au.pause();
        $(au).removeClass("chosen");
      }
      return true;
    });
  };

}).call(this);
