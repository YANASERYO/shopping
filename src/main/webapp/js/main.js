const scrollItems = document.querySelectorAll(".js-scroll-item");
const observer = new IntersectionObserver(
	(entries) => {
		entries.forEach((entry) => {
			if (entry.isIntersecting) {
				entry.target.classList.add("is-show");
				observer.unobserve(entry.target);
			}
		});
	},
	{
		threshold: 0.2
	}
);
scrollItems.forEach((item) => {
	observer.observe(item);

});