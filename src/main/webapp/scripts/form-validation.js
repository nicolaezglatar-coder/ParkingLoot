(() => {
    'use strict';

    for (const form of document.querySelectorAll('.needs-validation')) {
        form.addEventListener('submit', event => {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }
            form.classList.add('was-validated');
        });
    }
})();
