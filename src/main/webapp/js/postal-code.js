document.addEventListener("click", async function(e) {
	if (!e.target.classList.contains("postal-code-search-button")) return;

	const button = e.target;
	const postalCodeInput = document.getElementById(button.dataset.postalCodeId);
	const addressInput = document.getElementById(button.dataset.addressId);
	const message = document.getElementById(button.dataset.messageId);
	const postalCode = postalCodeInput.value.replace(/[-ー－]/g, "").trim();

	message.textContent = "";

	if (!/^\d{7}$/.test(postalCode)) {
		message.textContent = "郵便番号を7桁で入力してください。";
		return;
	}

	try {
		const response = await fetch(
			(button.dataset.contextPath || "") +
			"/api/postal-code?postalCode=" +
			encodeURIComponent(postalCode)
		);

		const data = await response.json();

		if (!data.found) {
			message.textContent = data.message || "住所が見つかりませんでした。";
			return;
		}

		postalCodeInput.value = postalCode.slice(0, 3) + "-" + postalCode.slice(3);
		addressInput.value = data.address;
		message.textContent = "住所を入力しました。";

	} catch (error) {
		console.error(error);
		message.textContent = "住所検索中にエラーが発生しました。";
	}
});