const userAvatar = $("#user-avatar");
const settingsButton = $("#settings-button");
const unauthenticatedContent = $(".unauthenticated");
const authenticatedContent = $(".authenticated");

export const authorizationHandler = {
    init: () => {
        authorizationHandler.checkIfUserLoggedIn();
    },
    checkIfUserLoggedIn: () => {
        $.get("/auth/logged", response => {
            if (response.details === null) {
                userAvatar.attr("src", "");
                settingsButton.attr("title", "");
                unauthenticatedContent.removeClass("hidden");
                if (!authenticatedContent.hasClass("hidden")) {
                    authenticatedContent.addClass("hidden");
                }
                return;
            }
            userAvatar.attr("src", response.details.avatarUrl);
            settingsButton.attr("title", response.details.username);
            if (!unauthenticatedContent.hasClass("hidden")) {
                unauthenticatedContent.addClass("hidden");
            }
            authenticatedContent.removeClass("hidden");
        });
    }
}