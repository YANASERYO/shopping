//スクロールで表示

const pageTopBtn = document.getElementById('js-scroll-top');
window.addEventListener("scroll", () => {
  const currentY = window.pageYOffset;
  if ( currentY > 100){
    setTimeout(function(){
      pageTopBtn.style.opacity = 1;
    }, 1);
  } else {
    setTimeout(function(){
      pageTopBtn.style.opacity = 0;
    }, 1);
  }
});