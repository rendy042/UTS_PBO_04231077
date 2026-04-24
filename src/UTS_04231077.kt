import java.util.Scanner

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
// Kelas Pengelola
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

        if (donatur.getSaldo() < nominal) {
            println("❌ Donasi ditolak: saldo tidak cukup.")
            return false
        }

        if (kampanye.getSaldo() + nominal > kampanye.getTarget()) {
            println("❌ Donasi ditolak: melebihi target maksimal.")
            return false
        }

        donatur.kurangiSaldo(nominal)
        kampanye.tambahSaldo(nominal)

        println("✅ Donasi berhasil sebesar Rp$nominal")
        println("Sisa Saldo Donatur: ${donatur.getSaldo()}")
        println("Total Kampanye: ${kampanye.getSaldo()}")

        return true
    }
}

// =======================
// Main Program (INTERAKTIF)
// =======================
fun main() {

    val input = Scanner(System.`in`)

    println("=== Sistem Donasi Mahasiswa ===")

    // Input data donatur
    print("Masukkan Nama Donatur: ")
    val nama = input.nextLine()

    print("Masukkan Saldo Awal: ")
    val saldo = input.nextDouble()

    val donatur = Donatur(1, nama, saldo)
    val kampanye = Kampanye(1, "Bantu Mahasiswa", 50000.0, 150000.0)
    val pengelola = Pengelola(1, "FSTI")

    val sistem = SistemDonasi()

    println("\nPengelola: ${pengelola.nama}")
    println("Kampanye: ${kampanye.nama}")
    println("Target: ${kampanye.getTarget()}")

    // Loop donasi
    while (true) {
        println("\nSaldo Anda: ${donatur.getSaldo()}")

        print("Masukkan nominal donasi (0 untuk keluar): ")
        val nominal = input.nextDouble()

        if (nominal == 0.0) {
            println("Terima kasih telah berdonasi 🙏")
            break
        }

        sistem.prosesDonasi(donatur, kampanye, nominal)
    }
}