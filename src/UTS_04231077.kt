// =======================
// Kelas Donatur
// =======================
class Donatur(
    val id: Int,
    val nama: String,
    private var saldo: Double
) {
    fun getSaldo(): Double {
        return saldo
    }

    fun kurangiSaldo(nominal: Double) {
        saldo -= nominal
    }
}

// =======================
// Kelas Kampanye
// =======================
class Kampanye(
    val id: Int,
    val nama: String,
    private var saldoTerkumpul: Double,
    private val targetMaksimal: Double
) {
    fun getSaldo(): Double {
        return saldoTerkumpul
    }

    fun getTarget(): Double {
        return targetMaksimal
    }

    fun tambahSaldo(nominal: Double) {
        saldoTerkumpul += nominal
    }
}

// =======================
// Kelas Pengelola king
// =======================
class Pengelola(
    val id: Int,
    val nama: String
)

// =======================
// Kelas Sistem Donasi
// =======================
class SistemDonasi {

    fun prosesDonasi(donatur: Donatur, kampanye: Kampanye, nominal: Double): Boolean {
        println("\n=== Proses Donasi ===")

        // Validasi saldo donatur
        if (donatur.getSaldo() < nominal) {
            println("❌ Donasi ditolak: saldo tidak cukup.")
            return false
        }

        // Validasi target maksimal
        if (kampanye.getSaldo() + nominal > kampanye.getTarget()) {
            println("❌ Donasi ditolak: melebihi target maksimal.")
            return false
        }

        // Proses transaksi
        donatur.kurangiSaldo(nominal)
        kampanye.tambahSaldo(nominal)

        println("✅ Donasi berhasil!")
        println("Saldo Donatur: ${donatur.getSaldo()}")
        println("Saldo Kampanye: ${kampanye.getSaldo()}")

        return true
    }
}

// =======================
// Main Program
// =======================
fun main() {

    val donatur = Donatur(1, "Rendy", 100000.0)
    val kampanye = Kampanye(1, "Bantu Mahasiswa", 50000.0, 150000.0)
    val pengelola = Pengelola(1, "FSTI")

    val sistem = SistemDonasi()

    println("=== Sistem Donasi Mahasiswa ===")
    println("Pengelola: ${pengelola.nama}")
    println("Donatur: ${donatur.nama}")
    println("Saldo Donatur: ${donatur.getSaldo()}")
    println("Target Kampanye: ${kampanye.getTarget()}")
    println("Saldo Awal Kampanye: ${kampanye.getSaldo()}")

    // Simulasi
    sistem.prosesDonasi(donatur, kampanye, 30000.0)
    sistem.prosesDonasi(donatur, kampanye, 80000.0)
    sistem.prosesDonasi(donatur, kampanye, 50000.0)
}