window.cart = {
    get: function() {
        const savedCart = localStorage.getItem('currentOrder');
        return savedCart ? JSON.parse(savedCart) : [];
    },

    save: function(cartItems) {
        localStorage.setItem('currentOrder', JSON.stringify(cartItems));
    },

    add: function(id, name, price, quantity) {
        let currentCart = this.get();
        const existingItemIndex = currentCart.findIndex(item => item.id === id);

        if (existingItemIndex > -1) {
            currentCart[existingItemIndex].quantity += quantity;
        } else {
            currentCart.push({ id: id, name: name, price: price, quantity: quantity });
        }
        this.save(currentCart);
        console.log(`Producto ${name} añadido/actualizado en el carrito.`);
    },

    remove: function(id) {
        let currentCart = this.get();
        currentCart = currentCart.filter(item => item.id !== id);
        this.save(currentCart);
    },

    updateQuantity: function(id, delta) {
        let currentCart = this.get();
        const itemIndex = currentCart.findIndex(item => item.id === id);

        if (itemIndex > -1) {
            let newQuantity = currentCart[itemIndex].quantity + delta;
            if (newQuantity < 1) newQuantity = 1;
            currentCart[itemIndex].quantity = newQuantity;
            this.save(currentCart);
        }
    },

    clear: function() {
        localStorage.removeItem('currentOrder');
    }
};
