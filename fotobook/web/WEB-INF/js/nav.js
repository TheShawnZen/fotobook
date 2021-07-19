const navSlide = (() => {
    const burger = document.querySelector('.burger');
    const nav = document.querySelector('.nav-links');
    const navLinks = document.querySelectorAll('.nav-links li');

    burger.addEventListener('click', () => {
        nav.classList.toggle('nav-active');
        navLinks.forEach((link, index) => {
            if (link.style.animation) {
                link.style.animation = '';
            } else {
                link.style.animation = `navLinkFade 0.5s ease forwards ${index / 7 + 0.5}s`;
            }
        });
        //burger animation
        burger.classList.toggle('icone');
    });
});


const friend = document.querySelector('.friendList');
const friendLinks = document.querySelectorAll('.friend-links li');
const btn_friend = document.querySelector('.btn_friend');

if(btn_friend!==null){
    const friendSlide = (() => {
        btn_friend.addEventListener('click', () => {
            friend.classList.toggle('friend-active');
            friendLinks.forEach((link, index) => {
                if (link.style.animation) {
                    link.style.animation = '';
                } else {
                    link.style.animation = `friendLinkFade 0.5s ease forwards ${index / 7 + 0.5}s`;
                }
            });
        });

    });
friendSlide();
}

navSlide();

var url = window.location.search;
if (url.split("&")[0] === "?friendRequest=true") {
    friend.classList.toggle('friend-active');
    friendLinks.forEach((link, index) => {
        if (link.style.animation) {
            link.style.animation = '';
        } else {
            link.style.animation = `friendLinkFade 0.5s ease forwards ${index / 7 + 0.5}s`;
        }
    });

}
